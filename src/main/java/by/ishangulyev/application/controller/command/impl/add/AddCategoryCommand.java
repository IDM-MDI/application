package by.ishangulyev.application.controller.command.impl.add;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.model.entity.impl.Category;
import by.ishangulyev.application.service.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddCategoryCommand implements ActionCommand {
    private CategoryService service = CategoryService.getInstance();
    @Override public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router;
        String name = request.getParameter("categoryName");
        if(name != null){
            Category add = new Category();
            add.setName(name);
            service.add(add);
        }
        router = service.get(request);

        return router;
    }
}
