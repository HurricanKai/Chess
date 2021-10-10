package de.nosswald.chess.utils;

import java.io.File;

/**
 * @author Nils Osswald
 * @author Noah Gerber
 */
public final class ResourceLocation
{
    private final String fileName;
    private final Type type;

    private final File file;

    /**
     * @param fileName  the name of the file
     * @param type      the type of the resource
     */
    public ResourceLocation(String fileName, Type type)
    {
        this.fileName = fileName;
        this.type = type;

        file = new File("assets/" + type.folder, fileName);
    }

    public enum Type
    {
        TEXTURE("textures/"),
        PIECE("textures/pieces/");

        private final String folder;

        Type(String folder)
        {
            this.folder = folder;
        }
    }

    public File getFile()
    {
        return file;
    }
}
