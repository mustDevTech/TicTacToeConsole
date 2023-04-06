import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The LanguageManager class provides methods for loading and retrieving language resources.
 * The class uses a ResourceBundle to handle different languages and a cache system for efficient resource retrieval.
 */
public final class LanguageManager
{
    private static ResourceBundle resourceBundle; // The ResourceBundle used to retrieve language resources.
    private static final Map<String, String> resourceCache = new ConcurrentHashMap<>(); // A cache of resource strings for efficient retrieval.
    private static final Map<String, MessageFormat> formattedCache = new ConcurrentHashMap<>(); // A cache of formatted MessageFormat objects for efficient retrieval.

    /**
     * This private constructor prevents the LanguageManager class from being instantiated.
     * The class should be used as a utility class.
     */
    private LanguageManager()
    {
        throw new AssertionError("LanguageManager is a utility class and should not be instantiated.");
    }

    /**
     * Loads a language resource bundle based on the specified language code.
     * Clears the resource cache and formatted cache to ensure a clean reload.
     * @param languageCode the language code to load the resource bundle for.
     */
    public static void loadLanguage(String languageCode)
    {
        resourceBundle = ResourceBundle.getBundle("tictactoe", new Locale(languageCode));
        resourceCache.clear();
        formattedCache.clear();
    }

    /**
     * Retrieves a language resource string based on the specified key and arguments.
     * Uses the resource cache and formatted cache for efficient retrieval.
     * Throws an IllegalStateException if the LanguageManager has not been initialized. Call loadLanguage() first.
     * @param key the key of the resource string to retrieve.
     * @param args the arguments to use when formatting the retrieved string (if applicable).
     * @return a String containing the retrieved resource string.
     */
    public static String getResource(String key, Object... args)
    {
        if (resourceBundle == null)
        {
            throw new IllegalStateException("LanguageManager has not been initialized. Call loadLanguage() first.");
        }
        MessageFormat format = formattedCache.get(key);
        if (format == null)
        {
            String pattern = resourceCache.get(key);
            if (pattern == null)
            {
                pattern = resourceBundle.getString(key);
                resourceCache.put(key, pattern);
            }
            format = new MessageFormat(pattern);
            formattedCache.put(key, format);
        }
        return format.format(args);
    }
}