package project.ipl;


;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;


class Ipl {
    String matchesData = "/home/pakinbasker/Desktop/java/programs/src/project/ipl/matches.csv";
    String deliveriesData = "/home/pakinbasker/Desktop/java/programs/src/project/ipl/deliveries.csv";
    Main m = new Main();
    int n=0;


    int matchDataLen,deliveriesLen;
    public String[] s ;
    public void setlenM()
    {
        matchDataLen=countSizeOfPath(matchesData);
        s=new String[matchDataLen];
    }
    public void setlenD()
    {
        deliveriesLen=countSizeOfPath(deliveriesData)+3;
        s=new String[deliveriesLen];
    }


    public void setter(String[] arr)
    {
        for (int i = 0; i < arr.length -1; i++)
        {
            s[i] = arr[i];
        }
    }

    public int countSizeOfPath(String mn) {
        int ans = 0;
        String line = "";

        try (BufferedReader bf = new BufferedReader(new FileReader(mn))) {
            while ((line = bf.readLine()) != null) {
                if (line != null) {
                    String[] s1 = line.split(",");
                    ans = s1.length;
                }
            }
        } catch (IOException e) {
            System.out.println("hiii");
        }

        return ans;
    }

    public void calculate(Map<String, Integer> hm, int index,int increment)
    {
        int count = hm.getOrDefault(s[index], 0);
        hm.put(s[index], count + increment);

    }
    public void calculate1(Map<String, Double> hm, int index,double increment)
    {

        double count = hm.getOrDefault(s[index], 0.0);
        hm.put(s[index], count + increment);

    }
}

public class Main {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    public static void main(String[] args) throws FileNotFoundException {
        String matchesData = "/home/pakinbasker/Desktop/java/programs/src/project/ipl/matches.csv";
        String deliveriesData = "/home/pakinbasker/Desktop/java/programs/src/project/ipl/deliveries.csv";
        System.out.println("Number of matches played per year of all the years in IPL\n");
        scenario1(matchesData);
        System.out.println("Number of matches won of all teams over all the years of IPL.\n");
        scenario2(matchesData);
        System.out.println("For the year 2016 get the extra runs conceded per team.\n");
        scenario3(deliveriesData);
        System.out.println("For the year 2015 get the top economical bowlers.\n");
        scenario4(deliveriesData);
        System.out.println("which batsman having highest striking rate\n");
        ownScenario(deliveriesData);

    }
    public static void wrongsolution(String deliveriesData)
    {
        Map<String, Double> hm = new TreeMap<>();
        String line = "";
        String str[] = new String[countsizeM(deliveriesData)];

        Ipl ipl=new Ipl();
        ipl.setlenD();
        double eco=0.0;

        try (BufferedReader bf = new BufferedReader(new FileReader(deliveriesData)))
        {
            bf.readLine();
            while ((line = bf.readLine()) != null)
            {
                str = line.split(",");
                ipl.setter(str);
                String bowler = str[8];
                int year = Integer.parseInt(str[0]);
                int totalRuns = Integer.parseInt(str[17]) - Integer.parseInt(str[11])-Integer.parseInt(str[12])  ;
                Integer.parseInt(str[11]);
                int ball = Integer.parseInt(str[5]);

                if ((year >= 518) && (year <= 576) && ball > 0)
                {
                    eco = (totalRuns / (double) ball) * 6;
                    ipl.calculate1(hm,8,eco/10);
                }






            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        int i=0;

        for (Map.Entry mm : hm.entrySet())
        {
            i++;
            System.out.println(i+" "+"Bowler : - " +mm.getKey() + "   &   " +"Economy "+ mm.getValue());
            System.out.println();
        }
        System.out.println("\n------------------------------------------------------------------------");

    }

    public static void scenario2(String matchesData){
        String line = "";
        Map<String, Integer> hm = new TreeMap<>();
        Ipl ipl = new Ipl();

        String s1[] = new String[countsizeM(matchesData)];

        ipl.setlenM();



        try( BufferedReader bf=new BufferedReader(new FileReader(matchesData));)
        {
            bf.readLine();

            while((line=bf.readLine())!=null)
            {
                if(line!=null)
                {
                    s1=line.split(",");

                    ipl.setter(s1);
                    ipl.calculate(hm,10,1);
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        //Map<String,Integer> orderMap=new TreeMap<>(hm);
        int i=0;
        for(Map.Entry map: hm.entrySet()){
            i++;
            System.out.println(i+")  Team : - "+map.getKey()+"&"+"No Of Matches won = "+map.getValue());
            System.out.println();
        }
        System.out.println("--------------------------------------------------------------");

    }
    public static void scenario3(String deliveriesData)
    {
        String line = "";
        Map<String, Integer> hm = new TreeMap<>();
        Ipl ipl = new Ipl();
        ipl.setlenD();
        String str[] = new String[countsizeM(deliveriesData)];


        try (BufferedReader bf = new BufferedReader(new FileReader(deliveriesData));) {

            bf.readLine();
            while ((line = bf.readLine()) != null) {
                if (line != null) {
                    str = line.split(",");
                    ipl.setter(str);
//                    if(s[1]=="577"){
//                    ipl.calculate(hm,1);
                    int year =Integer.parseInt(str[0]);
                    String bowling=str[3];
                    int run=Integer.parseInt(str[16]);

                    if(year>=577) {
                        ipl.calculate(hm,3,run);

                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        int i=0;
        for (Map.Entry mm : hm.entrySet())
        {
            i++;
            System.out.println(i+")  Bowling Team : - " +mm.getKey() + "   &   " +"Extra Runs "+ mm.getValue());
            System.out.println();
        }
        System.out.println("\n------------------------------------------------------------------------");
    }

    public static LinkedHashMap sortMap(LinkedHashMap map) {
        List <Map.Entry<String, String>> capitalList = new LinkedList<>(map.entrySet());

        // call the sort() method of Collections
        Collections.sort(capitalList, (l1, l2) -> l1.getValue().compareTo(l2.getValue()));

        // create a new map
        LinkedHashMap<String, String> result = new LinkedHashMap();

        // get entry from list to the map
        for (Map.Entry<String, String> entry : capitalList) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
    public static void scenario4(String deliveriesData) {
        String line = "";
        String str[] = new String[21];
        int i = 0, totalRuns = 0;

        String bowler = "";

        TreeSet<String> set = new TreeSet<>(getterr());
        TreeMap<String, Integer> hm = new TreeMap<>();
        TreeMap<String, Integer> ball = new TreeMap<>();
        TreeMap<String, Double> over = new TreeMap<>();
        TreeMap<String, Double> economy = new TreeMap<>();


        try (BufferedReader bf = new BufferedReader(new FileReader(deliveriesData))) {
            bf.readLine();

            while ((line = bf.readLine()) != null) {
                str = line.split(",");
                int id = Integer.parseInt(str[0]);
                if (id >= 518 && id < 577) {
                    bowler = str[8];
                }


                int total = Integer.parseInt(str[17]) - (Integer.parseInt(str[11]) + Integer.parseInt(str[12]) + Integer.parseInt(str[14]));
                int balls = Integer.parseInt(str[5]);
                int wide = Integer.parseInt(str[10]);
                int noball = Integer.parseInt(str[13]);
                if (balls != 0 && wide == 0 && noball == 0) {
                    ball.put(bowler, ball.getOrDefault(bowler, 0) + 1);

                }

                hm.put(bowler, hm.getOrDefault(bowler, 0) + total);

            }
            for (String item : set) {
                over.put(item, (double) ball.get(item) / 6);
            }
            for (String item : set) {
                economy.put(item, (double) (hm.get(item) / over.get(item)));
            }
        } catch (Exception e) {
            System.out.println("failed in this case" + e);
        }

        List<Map.Entry<String, Double>> sortedEntries = sortMapByValue(economy);
        int j=0;
        for (Map.Entry<String, Double> map : sortedEntries) {
            j++;
            if(j==6){
                break;
            }
            System.out.println(j+")  Bowler name :- "+map.getKey()+"   &  Economy of bowler  ->  "+df.format(map.getValue()));
            System.out.println();
            // System.out.println(entry.getKey() + ": " + String.format("%.2f",entry.getValue()));
        }
        System.out.println("---------------------------------------------------------------------------------------------");
//        int j = 0;
//        for (Map.Entry map : economy.entrySet()) {
//            j++;
//            System.out.println(j + ")  Bowler name :- " + map.getKey() + "   &  Economy of bowler  ->  " + df.format(map.getValue()));
//            System.out.println();
//        }
//
//        Map<String, String> result = sortMap(economy);
//        for (Map.Entry entry : result.entrySet()) {
//            System.out.print("Key: " + entry.getKey());
//            System.out.println(" Value: " + entry.getValue());
//        }


    }
    public static List<Map.Entry<String, Double>> sortMapByValue(Map<String, Double> map) {
        List<Map.Entry<String, Double>> entries = new ArrayList<>(map.entrySet());
        Collections.sort(entries, Map.Entry.comparingByValue());
        return entries;
    }
    //    public static List<Map.Entry<String, Double>> sortMapByValue1(Map<String, Double> map) {
//        List<Map.Entry<String, Double>> entries = new ArrayList<>(map.entrySet());
//        Collections.sort(entries, Collections.reverseOrder());
//        return entries;
//    }
    public static void scenario1(String matchesData){
        String line = "";
        Map<String, Integer> hm = new TreeMap<>();
        Ipl ipl = new Ipl();

        String s1[] = new String[countsizeM(matchesData)];
        ipl.setlenM();



        try( BufferedReader bf=new BufferedReader(new FileReader(matchesData));)
        {
            bf.readLine();

            while((line=bf.readLine())!=null)
            {
                if(line!=null)
                {
                    s1=line.split(",");

                    ipl.setter(s1);
                    ipl.calculate(hm,1,1);
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        int i=0;
        for(Map.Entry map: hm.entrySet())
        {
            i++;
            System.out.println(i+")  Year = "+map.getKey()+"   &   "+" No Of Matches = "+map.getValue());
            System.out.println();
        }


        System.out.println("-------------------------------------------------------------------");
    }

    public static void ownScenario(String deliveriesData) {
        String line = "";
        String s[] = new String[21];
        int i = 0, totalRuns = 0;
        //ge();
        String bowler = "";
        Set<String> batsman = new TreeSet<>(getPlayerName());
        TreeMap<String, Double> map = new TreeMap<>();
        TreeMap<String, Double> ballFaced = new TreeMap<>();
        TreeMap<String, Double> overAllStrikeRate = new TreeMap<>();

        try (BufferedReader bf = new BufferedReader(new FileReader(deliveriesData))) {
            bf.readLine();
            while ((line = bf.readLine()) != null) {
                s = line.split(",");
                String bats = s[6];
                int run = Integer.parseInt(s[15]);
                ballFaced.put(bats, ballFaced.getOrDefault(bats, 0.0) + 1);
                map.put(bats, map.getOrDefault(bats, 0.0) + run);
            }

            for (String item : batsman) {
                overAllStrikeRate.put(item, (map.get(item) / ballFaced.get(item)) * 100);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        // List<Map.Entry<String, Double>> sortedEntries = sortMapByValue(overAllStrikeRate);
//
        Map<String, Double> sortedMap = sortMapByValueDescending(overAllStrikeRate);

        System.out.println("Sorted Map:");
        int j=0;
        for (Map.Entry<String, Double> entry : sortedMap.entrySet()) {
            j++;
            if(j==11){break;}
            System.out.println(j+")   batsman   ->"+entry.getKey() + "    -> Strike rate :  " + df.format(entry.getValue()));
        }
/*
        for(Map.Entry k:sortedEntries){
            System.out.println(k.getKey()+"  "+k.getValue());

*/

    }
    public static Map<String, Double> sortMapByValueDescending(TreeMap<String, Double> map) {
        List<Map.Entry<String, Double>> entries = new ArrayList<>(map.entrySet());

        Collections.sort(entries, Collections.reverseOrder(Map.Entry.comparingByValue()));

        LinkedHashMap<String, Double> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
    public static Set getPlayerName() {
        String matchesData = "/home/pakinbasker/Desktop/java/programs/src/project/ipl/matches.csv";
        String deliveriesDat = "/home/pakinbasker/Desktop/java/programs/src/project/ipl/deliveries.csv";
        String line = "";
        String s1[] = new String[21];
        String[] name = new String[461];


        TreeSet<String> set = new TreeSet<>();

        try (BufferedReader bf = new BufferedReader(new FileReader(deliveriesDat))) {
            bf.readLine();

            while ((line = bf.readLine()) != null) {
                s1 = line.split(",");

                int id = Integer.parseInt(s1[0]);

                set.add(s1[6]);

            }
        } catch (Exception e) {
            System.out.println("failed in this case" + e);
        }
        return set;


    }

    public static int countsizeM(String mn) {
        int ans = 0,i=0;
        String line = "";

        try (BufferedReader bf = new BufferedReader(new FileReader(mn))) {


            while ((line = bf.readLine()) != null) {
                if(i==1){break ;}

                if (line != null) {
                    String[] s1 = line.split(",");
                    ans = s1.length;
                }
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        return ans;
    }
    public static Set getterr(){
        String matchesData = "/home/pakinbasker/Desktop/java/programs/src/project/ipl/matches.csv";
        String deliveriesDat = "/home/pakinbasker/Desktop/java/programs/src/project/ipl/deliveries.csv";
        String line="";
        String s1[] =new String[21];
        String[] name=new String[99];


        TreeSet<String> set = new TreeSet<>();

        try(BufferedReader bf =new BufferedReader(new FileReader(deliveriesDat))){
            bf.readLine();

            while((line=bf.readLine())!=null){
                s1=line.split(",");

                int id = Integer.parseInt(s1[0]);
                if (id >= 518 && id < 577)
                {
                    set.add(s1[8]);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("failed in this case"+e);
        }
        return set;


    }
}
