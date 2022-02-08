package by.ishangulyev.application.service;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.RouterType;
import by.ishangulyev.application.controller.command.JspPath;
import by.ishangulyev.application.dao.impl.DaoBattery;
import by.ishangulyev.application.dao.impl.DaoCategory;
import by.ishangulyev.application.exception.DaoException;
import by.ishangulyev.application.model.entity.impl.Battery;
import by.ishangulyev.application.model.entity.impl.Category;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class CategoryService {
    private static CategoryService instance = new CategoryService();
    private CategoryService(){}

    public static CategoryService getInstance() {
        return instance;
    }

    public boolean add(Category add){
        DaoCategory daoCategory = new DaoCategory();
        return daoCategory.create(add);
    }
    public boolean update(Category update){
        DaoCategory daoCategory = new DaoCategory();
        return daoCategory.update(update);
    }
    public boolean delete(String id){
        DaoCategory daoCategory = new DaoCategory();
        long realId;
        try{
            realId = Long.parseLong(id);
        }
        catch (NumberFormatException e){
            realId = -1;
        }
        return daoCategory.delete(realId);
    }
    public Router get(HttpServletRequest request){
        String page = request.getParameter("page");
        if(page == null || page.isEmpty()){
            page = "1";
        }
        int pageNumber = Integer.parseInt(page);
        int next = 0,prev = pageNumber-1;
        List<Category> categoryList = null;
        DaoCategory daoCategory = new DaoCategory();
        try {
            pageNumber--;
            categoryList = daoCategory.findByCount(pageNumber);
            if(categoryList.size() > 9){
                next = pageNumber+1;
                categoryList.remove(categoryList.size()-1);
            }
        } catch (DaoException e) {
            // TODO: 2/8/2022
        }
        request.setAttribute("categoryList",categoryList);
        request.setAttribute("currentPage",++pageNumber);
        request.setAttribute("nextPage",next);
        request.setAttribute("prevPage",prev);
        return new Router(JspPath.CATEGORY_SETTINGS, RouterType.FORWARD);
    }
}