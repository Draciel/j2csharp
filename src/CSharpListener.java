import java.util.ArrayList;
import java.util.List;

class CSharpListener extends Java9BaseListener {

    private List<String> errors = new ArrayList<>();

    @Override
    public void enterMethodDeclaration(final Java9Parser.MethodDeclarationContext ctx) {
        final Java9Parser.MethodHeaderContext header = ctx.methodHeader();
        final Java9Parser.MethodBodyContext body = ctx.methodBody();
        final String methodName = header.getText();

        final String bodyName = body.getText();

        System.out.println("Method " + methodName);
        System.out.println("Body " + bodyName);
    }

    @Override
    public void enterMethodDeclarator(final Java9Parser.MethodDeclaratorContext ctx) {
        final Java9Parser.IdentifierContext context = ctx.identifier();
        final String methodName = context.getText();

        if (Character.isUpperCase(methodName.charAt(0))) {
            String error = String.format("Method %s is uppercased!", methodName);
            errors.add(error);
        }
    }
}
