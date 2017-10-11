package set;
import java.util.HashSet;
import java.util.Iterator;
import map.Country;

public class HashSetTest{
  public static void main(String[] args){
    Country india=new Country("India",1000);
    Country japan=new Country("Japan",1000);

    Country france=new Country("France",2000);
    Country russia=new Country("Russia",2000);

    HashSet<Country> hashSet = new HashSet<Country>(5);
    hashSet.add(india);
    hashSet.add(japan);
    hashSet.add(france);
    hashSet.add(russia);

    Iterator<Country> iterator = hashSet.iterator();
    while(iterator.hasNext())
    {
        Country countryObj=iterator.next();
        // String capital=iterator.get(countryObj);
        System.out.println(countryObj.getName()+"----"+countryObj.getPopulation());
    }
  }
}
