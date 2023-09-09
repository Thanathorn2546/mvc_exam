

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;

public class MOMLController {
     MOMLModel insMOMLModel = new MOMLModel();
     //CSGOModel2 insCSGOModel2 = new CSGOModel2();
     MOMLView insMOMLView = new MOMLView(); 
     
    public void process() {
         String ls_path = "D:\\Java-Project\\MOML_test.txt";
         String ls_data_row = "";
         String ls_data_word = "";
         String ls_result = "";
         String ls_tag_name_open = "";
         String ls_tag_name_close = ""; 
         String ls_tag_name_open_word = "";
         String ls_tag_name_close_word = "";
         String ls_desc = "";
         int li_pos = 0;
         int li_begin = 0;
         int li_end = 0;
         
        try {
            FileReader fr = new FileReader(ls_path);
            BufferedReader br = new BufferedReader(fr);

            while((ls_data_row = br.readLine()) != null) {          
                insMOMLModel.setModelData(ls_data_row);
                //insMOMLModel2.setModel2Data(insMOMLModel.getModelData().split(" "));
                                            
                while(ls_data_row.length() != 0 ) {
                    li_pos = ls_data_row.indexOf("<");
                    //System.out.println(li_pos);  
             
                    if(li_pos == -1 ) {
                      ls_data_word = ls_data_row;
                      ls_result = "[UNTAGGED]{"+ls_data_row+"}";
                      
                      System.out.println(ls_data_word);                    
                      System.out.println(ls_result);
                      ls_data_row = "";                       
                      continue;
                    } 
                    if(li_pos > 0 ) {
                      System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                      ls_data_word = ls_data_row.substring(0, li_pos); // - 1);
                      ls_result = ls_data_row.substring(0, li_pos);  
                      ls_data_row = ls_data_row.replace(ls_data_word, "");
                      
                      System.out.println(ls_data_word);                   
                      System.out.println(ls_result);                 
                      System.out.println(ls_data_row);
                      //break;
                      System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");  

                    }
                    else { 
                        li_pos = ls_data_row.indexOf("/>");
                        if(li_pos > 0 ) {
                          System.out.println("xxx");
                          ls_data_word = "";
                          ls_result = "";
                          ls_data_row = "";                         
                        } 
                                                  
                        li_begin = ls_data_row.indexOf("<");
                        li_end = ls_data_row.indexOf(">");
                        ls_tag_name_open = ls_data_row.substring(li_begin, li_end + 1);
                        ls_tag_name_close = ls_tag_name_open.substring(0,1)+"/"+
                                            ls_tag_name_open.substring(1,li_end + 1);

                        //System.out.println(ls_tag_name_open); 
                        //System.out.println(ls_tag_name_close);                   
                        //System.out.println(ls_tag_name_open.length()); 
                        //break;
                        ls_tag_name_open_word = ls_tag_name_open.substring(ls_tag_name_open.length() - 2);
                        ls_tag_name_close_word = ls_tag_name_close;

                        li_begin = ls_data_row.indexOf(ls_tag_name_open_word);
                        li_end = ls_data_row.indexOf(ls_tag_name_close_word);
                        //System.out.println(li_begin); 
                        //System.out.println(li_end);

                        ls_data_word = ls_tag_name_open + ls_data_row.substring(li_begin + 2,li_end) + ls_tag_name_close;

                        ls_result = ls_data_row.substring(li_begin + 2,li_end);                   
                     }  
                    //System.out.println(ls_data_word);  
                    insMOMLView.PrintResult(ls_result);  
                    //System.out.println(ls_result);
                    ls_data_row = ls_data_row.replace(ls_data_word, "");             
                    //System.out.println(ls_data_row); 
                    //System.out.println(ls_data_row.length());
                } //-- end while
                //break;
            } //-- end while
            br.close();   
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
}

