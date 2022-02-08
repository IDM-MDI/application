package by.ishangulyev.application.controller.command.impl.delete;

import by.ishangulyev.application.controller.Router;
import by.ishangulyev.application.controller.command.ActionCommand;
import by.ishangulyev.application.model.entity.impl.Category;
import by.ishangulyev.application.service.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteCategoryCommand implements ActionCommand {
    private CategoryService service = CategoryService.getInstance();
    @Override public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router;
        String textId = request.getParameter("categoryId");
        if(textId != null){
            service.delete(textId);
        }
        router = service.get(request);
        return router;
    }
}
