package translator;

import utility.Nonnull;

//todo find better name to avoid confusion with translator and this interface
public interface PartialTranslator<T> {
    @Nonnull
    String translate(@Nonnull final T input, final int indentationCounter);
}
