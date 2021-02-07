/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quettainstitute;

/**
 *
 * @author QI-6
 */
public class N2W_MultipleLanguages {
    
    
    
    
   String[] u={"","ایک","دو","تین","چار","پانچ","چھ","سات","آٹھ","نو","دس","گیارہ","بارہ","تیرہ","چودہ","پندرہ","سولہ","سترہ","اٹھارہ","انیس","بیس","اکیس","بائیس","تیئیس","چوبیس","پچیس","چھبیس","ستائیس","اٹھائیس","انتیس","تیس","اکتیس","بتیس","تینتیس","چونتیس","پینتیس","چھتیس","سینتیس","اٹھتیس","انتالیس","چالیس","اکتالیس","بیا لیس","تینتالیس","چوالیس","پینتالیس","چھیالیس","سینتالیس","اٹھتالیس","انچاس","پچاس","اکاون","باون","تریپن","چون","پچپن","چھپن","ستاون","اٹھاون","انسٹ","ساٹھ","اکسٹھ","باسٹھ","تریسٹھ","چونسٹھ","پینسٹھ","چھیا سٹھ","ستاسٹھ","اٹھاسٹھ","انتر","ستر","اکتر","بہتر","تہتر","چوھتر","پچھتر","چھہتر","ستتر","اٹھتر","اناسی","اسی","اکاسی","بیاسی","تراسی","چوراسی","پچھاسی","چھیاسی","ستاسی","اٹھاسی","نواسی","نوے","اکانوے","بیانوے","تیرانوے","چورانوے","پچھانوے","چھیانوے","ستانوے","اٹھانوے","نینانوے"}; // from one to 100
   String[] h={"سو","ہزار","لاکھ","کروڑ","ارب","کھرب"}; 
   
   public String get(int n){
     number=n;
     output=""; 
     doProcess(); 
   return output; 
   }
   
   int number=0; 
   String output=""; 
   
   private void doProcess(){
   if(number<100){output+=u[number];      }else
   if(number<1000){ //Hundreds
       int hs=number/100; 
       output+=" "+u[hs]+" "+h[0];
       number-=100*hs; 
       doProcess(); 
       //System.out.println("N is = "+);
   } else
       
       if(number<100000){ //thousands
       int ths=number/1000; 
       output+=" "+u[ths]+" "+h[1];
       number-=1000*ths; 
       doProcess(); 
       //System.out.println("N is = "+);
          // System.out.println("Pakistan is a greate country");
   }else
     
      
           if(number<10000000){ //Hundred Thousands
       int ths=number/100000; 
       output+=" "+u[ths]+" "+h[2];
       number-=100000*ths; 
       doProcess(); 
   }else
           if(number<1000000000){
       int ths=number/10000000; 
       output+=" "+u[ths]+" "+h[3];
       number-=10000000*ths; 
       doProcess(); 
       }
   
   }
}
