package payload;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import org.xml.sax.SAXException;

/**
 * @author prakhar
 */
public class ExtractorEngine {

	static ExtractorConfig conf = new ExtractorConfig();

	static ExtractorParser parser = new ExtractorParser();

	static List<List<Object>> getDimensionData(List<?> cookedObjects) throws SAXException, IOException {

		ExtractorParser.extractConfig();
		List<List<Object>> contList = new ArrayList<>();
		List<Function<Object, Object>> opFuncs = ExtractorConfig.getAll();

		cookedObjects.parallelStream().forEach(cookedObj -> {
			List<Object> extractedValues = new ArrayList<>();
			for (int jj = 0; jj < opFuncs.size(); jj++) {
				Function<Object, Object> function = opFuncs.get(jj);
				Object applyVal = function.apply(cookedObj);
				extractedValues.add(applyVal);
			}
			contList.add(extractedValues);
		});

		return contList;
	}

	static List<List<Object>> getDimensionDataSequential(List<?> cookedObjects) throws SAXException, IOException {
		ExtractorParser.extractConfig();
		List<List<Object>> contList = new ArrayList<>();
		List<Function<Object, Object>> opFuncs = ExtractorConfig.getAll();
		for (int ii = 0; ii < cookedObjects.size(); ii++) {
			Object a = cookedObjects.get(ii);
			List<Object> extractedValues = new ArrayList<>();
			for (int jj = 0; jj < opFuncs.size(); jj++) {
				Function<Object, Object> function = opFuncs.get(jj);
				Object applyVal = function.apply(a);
				extractedValues.add(applyVal);
			}
			contList.add(extractedValues);
		}
		return contList;
	}

	public static void main(String[] args)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SAXException, IOException {

		List<?> createObj = TestObjCreator.createObj(1e4);
		Long d1 = new Date().getTime();
		List<?> dimensionData = getDimensionData(createObj);
		Long d2 = new Date().getTime();
		System.out.println(d2 - d1);
		System.out.println(dimensionData);

	}

}
