#Payload Configuration Utility

Whats is an Extractor?
An extractor is a java.util.function describing how you extract a value for a given field, it taken an object and returns an object, use toString() on the extracted object to see the value of the field.

Why do we need extractors?
- To reduce the volume of data transferred over the wire, esp. when ony the string representation of output suffices and the entire domain structure is not needed.

How to configure the extractors?
- Use payload-config.xml to configure your extractors

What is the structure and contract for an extractor?
- All extractors are currently written in Extractors.java as static Function<Object,Object> 

What will be the output and input format ?
- The Extractor engine accepts a List<?> and outputs a List<List<Object>> , the inner list consists of fields extracted from each element of the input list based on the Extractor Configuration.

What are we using for parsing?
- jOOX is a libray which lets you parse XML using jQuery syntax.

Components
- Extractor Engine
- Extractor Config
- Extractor Parser 
