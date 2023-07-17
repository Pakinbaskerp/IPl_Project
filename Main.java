package project.ipl;



import java.io.*;
import java.util.*;

import static java.io.FileDescriptor.in;


class Ipls{
    int column;
    int i=0;
    String[] s=new String[17];

//    Ipls(int size){
//        this.size=size;
//    }
    public void setter(String[] arr){
        for (int i=0;i<arr.length-1;i++){
            s[i]=arr[i];
        }
    }
    public  String getter(int c){
        return s[c];
    }
    public void calculate(Map m){
        m.put(s[1],i++);

    }


}



public class Main
{

    public static void main(String[] args) throws FileNotFoundException
    {
        String matchesData = "/home/pakinbasker/Desktop/java/programs/src/project/ipl/matches.csv";
        String deliveriesData = "/home/pakinbasker/Desktop/java/programs/src/project/ipl/deliveries.csv";
        String line="";
        Map<String, Integer> hm=new HashMap<>();
        Ipls ipl=new Ipls();
        int i=0,count=0;
        String s1[] =new String[17];


        try( BufferedReader bf=new BufferedReader(new FileReader(matchesData));)
       {
           bf.readLine();
         while((line=bf.readLine())!=null)
         {
             if(line!=null)
             {
             s1=line.split(",");
//             count =s1.length+1;
             //hm.put(s1[1],hm.getOrDefault(s1[1],0)+1);
                 // hm.putIfAbsent(s1[1]=
                 ipl.setter(s1);
             hm.putIfAbsent(ipl.getter(1), 0);
             hm.put( ipl.getter(1), hm.get(ipl.getter(1)) + 1);
                 //   System.out.print(s1[1]+","+i+++"  - ");


             }

         }
//           System.out.println(count);
       }
       catch (IOException e){
           e.printStackTrace();
       }

        Map<String,Integer> orderMap=new TreeMap<>(hm);
//        System.out.println(s1[1]);
        for(Map.Entry map: hm.entrySet()){
            System.out.println("Year = "+map.getKey()+" & "+"No Of Matches = "+map.getValue());
        }

            }



    public static int countsize()
    {
        String matchesData = "/home/pakinbasker/Desktop/java/programs/src/project/ipl/matches.csv";
        int ans=0;
        String line="";
        try( BufferedReader bf=new BufferedReader(new FileReader(matchesData));)
        {
            while(bf.readLine()!=null)
            {
                line=bf.readLine();
                if(line!=null)
                {
                    String[] s1 = line.split(",");
                    ans = s1.length ;
                }
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }



        return ans;
    }
}