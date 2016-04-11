package payload;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;

import org.apache.commons.beanutils.PropertyUtils;

import domain.J;

/**
 * 
 * Container for list of static Extractors
 * @author prakhar
 *
 */
public class Extractors {
	
	/**
	 * Sample extractor 1
	 * @param config
	 * @return
	 */
	public static Function<Object, Object> cExtractor(String config) {
		return (arg) -> {
			// arg is unused here
			J targ = (J) arg;
			try {
				return targ.getI().getH().getG().getF().getE().getD().getC().getB().getA().getA1();
			} catch (Exception e) {
				return arg;
			}
		};
	}
	
	/**
	 * Generic nested value extractor
	 * @param hierarchy
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static Function<Object, Object> nestedValueEvaluator(String hierarchy)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		return (runtimeObjInstance) -> {
			try {
				return PropertyUtils.getNestedProperty(runtimeObjInstance, (String) hierarchy);
			} catch (Exception e) {
				// e.printStackTrace();
			}
			return runtimeObjInstance;
		};
	}

}
