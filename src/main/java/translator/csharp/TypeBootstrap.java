package translator.csharp;

import data.Type;
import translator.ComponentTranslator;
import utility.Nonnull;

import java.util.HashMap;

class TypeBootstrap implements ComponentTranslator<Type> {

    private final HashMap<Type, Type> typesTranslationMap = new HashMap<>();

    private TypeBootstrap() {
        typesTranslationMap.put(new Type("boolean"), new Type("bool"));
        typesTranslationMap.put(new Type("String"), new Type("string"));
        //no instance
    }

    public static TypeBootstrap instance() {
        return Holder.INSTANCE;
    }

    @Nonnull
    @Override
    public String translate(@Nonnull final Type input, final int indentationCounter) {
        final Type translatedType = typesTranslationMap.get(input);

        if (translatedType == null) {
            return input.literal();
        }

        return translatedType.literal();
    }

    private final static class Holder {
        private static final TypeBootstrap INSTANCE = new TypeBootstrap();
    }
}
