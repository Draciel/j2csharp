package visitor;

import data.Field;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

class FieldVisitor extends Java9BaseVisitor<Field> {

    @Override
    public Field visitFieldDeclaration(Java9Parser.FieldDeclarationContext ctx) {
        final String name = ctx.getText();
        System.out.println("name " + name);
        return new Field(name);
    }
}
