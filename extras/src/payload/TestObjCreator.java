package payload;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import domain.A;
import domain.B;
import domain.C;
import domain.D;
import domain.E;
import domain.F;
import domain.G;
import domain.H;
import domain.I;
import domain.J;

/**
 * Create a 10 level deep domain object for extraction testing
 * 
 * @author prakhar
 *
 */
public class TestObjCreator {
	
	/**
	 * Create a list of given size
	 * @param size
	 * @return
	 */
	public static List<?> createObj(double size){
		
		List<J> jList = new ArrayList<>();
		
		for(int ii=0; ii<size; ii++){
			
			A a = new A();
	        B b = new B();
	        C c = new C();
	        D d = new D();
	        E e = new E();
	        F f = new F();
	        G g = new G();
	        H h = new H();
	        I i = new I();
	        J j = new J();
	        
	        a.setA1(ii);
	        a.setB1(Math.random());
	        //a.setC1(new BigDecimal(Math.random()));
	        //a.setD1(UUID.randomUUID().toString());
	        
	        b.setA(a);
	        c.setB(b);
	        d.setC(c);
	        e.setD(d);
	        f.setE(e);
	        g.setF(f);
	        h.setG(g);
	        i.setH(h);
	        j.setI(i);

	        
	        jList.add(j);
		}
		return jList;
	}
	
}
