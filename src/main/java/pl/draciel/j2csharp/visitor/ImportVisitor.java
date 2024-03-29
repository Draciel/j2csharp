package pl.draciel.j2csharp.visitor;

import pl.draciel.j2csharp.data.Import;
import generated.Java9BaseVisitor;
import generated.Java9Parser;

final class ImportVisitor extends Java9BaseVisitor<Import> {

    private ImportVisitor() {

    }

    public static ImportVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public Import visitImportDeclaration(final Java9Parser.ImportDeclarationContext ctx) {
        if (ctx.singleTypeImportDeclaration() != null) {
            return new Import(false, ctx.singleTypeImportDeclaration().typeName().packageOrTypeName().getText(),
                    ctx.singleTypeImportDeclaration().typeName().identifier().getText());
        }
        if (ctx.singleStaticImportDeclaration() != null) {
            return new Import(true, ctx.singleStaticImportDeclaration().typeName().packageOrTypeName().getText(),
                    ctx.singleStaticImportDeclaration().identifier().getText());
        }
        throw new IllegalArgumentException("something went wrong during parsing!");
    }

    private static class Holder {
        private static final ImportVisitor INSTANCE = new ImportVisitor();
    }
}
