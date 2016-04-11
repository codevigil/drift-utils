package payload;

import static org.joox.JOOX.$;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

import org.joox.Match;
import org.springframework.util.ReflectionUtils;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Parses the payload-config.xml file to evaluate the extractors configured there and populate
 * ExtractorConfig with it.
 * @author prakhar
 *
 */
public class ExtractorParser {
	
	/**
	 * Eagerly store all the declared methods in Extractors.class in a map from method name to method ref.
	 * example key payload.Extractors.cExtractor, corresponding value , the actual reference of the static method
	 */
    // Static Caches to speed up introspection.
    private static final HashMap<String, Method> declaredMethodCache = new HashMap<>();
    
    /**
     * Loads up the declaredMethodCache
     * @param classes : List of classes to be instrospected for extractors.
     */
	public static void loadMethodRefMap(List<Class<?>> classes) {
    	for(int i=0 ; i<classes.size(); i++){
    		Class<?> classId = classes.get(i);
    		Method[] allDeclaredMethods = ReflectionUtils.getAllDeclaredMethods(classId);
    		for(int jj=0; jj<allDeclaredMethods.length; jj++){
    			Method method = allDeclaredMethods[jj];
    			//method-overloading tossed
    			String methodKey = method.getDeclaringClass().getName().concat(".").concat(method.getName());
    			declaredMethodCache.put(methodKey, method);
    		}
    	}
    	//System.out.println(declaredMethodCache);
    }
    
	/**
	 * Read up the payload-config file and store the configuration in ExtractorConfig
	 * 
	 * @throws SAXException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static void extractConfig() throws SAXException, IOException {		
		ExtractorParser.loadMethodRefMap(Collections.singletonList(Extractors.class));
		Match $document = $(new File("./payload-config.xml"));
		Match $columns = $document.find("column");
		$columns.forEach(($column)->{
			
			String id        = getTagValue($column,"id");
			String name      = getTagValue($column,"name");
			String extractor = getTagValue($column,"extractor");
			String attr      = getTagValue($column,"attr");
			
			try {
				ExtractorConfig.putConfig(extractor, (Function<Object,Object>) declaredMethodCache.get(extractor).invoke(null,attr));
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}			
			
		});
		System.out.println($columns.find("extractor"));
	}
	
	/**
	 * Helper to extract a value from a node
	 * @param rootNode
	 * @param tag
	 * @return
	 */
	private static String getTagValue(Element rootNode,String tag){
		NodeList idNode = rootNode.getElementsByTagName(tag);
		return idNode.item(0).getTextContent();
	}

}
