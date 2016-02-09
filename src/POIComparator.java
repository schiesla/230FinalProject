import java.util.Comparator;

public class POIComparator  implements Comparator<PointofInterest> {

	public POIComparator(){
		super();
	}

	@Override
	public int compare(PointofInterest poi1, PointofInterest poi2) {
		
		if(poi1.getRating() > poi2.getRating()) return 1;
		else if(poi1.getRating() < poi2.getRating()) return -1;
		else return( poi1.equals(poi2)? 0 : 1);
	}

}
