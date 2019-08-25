package pl.draciel.j2csharp.translator;

import pl.draciel.j2csharp.utility.Nonnull;

//todo find better name to avoid confusion with translator and this interface
public interface ComponentTranslator<T> {
    @Nonnull
    String translate(@Nonnull final T input, final int indentationCounter);
}
