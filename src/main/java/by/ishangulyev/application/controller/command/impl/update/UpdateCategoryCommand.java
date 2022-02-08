package by.ishangulyev.application.controller.command.impl.update;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.model.entity.impl.Category;
import by.ishangulyev.application.service.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateCategoryCommand implements ActionCommand {
    private CategoryService service = CategoryService.getInstance();
    @Override public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router;
        String name = request.getParameter("categoryName");
        String textId = request.getParameter("categoryId");
        if(name != null && textId != null){
            try{
                Category update = new Category();
                update.setName(name);
                update.setId(Long.parseLong(textId));
                service.update(update);
            }catch (NumberFormatException e){
                // TODO: 2/8/2022
            }
        }
        router = service.get(request);
        return router;
    }
}
