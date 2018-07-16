package com.sist.R;

import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.stereotype.Component;

@Component
public class NewsRManager {

	public void rWordCloud(String data)
    {
  	  try
  	  {
  		  RConnection rc=new RConnection();
  		  rc.voidEval("library(KoNLP)");
  		  rc.voidEval("library(wordcloud2)");
  		  rc.voidEval("library(webshot)");
  		  rc.voidEval("library(htmlwidgets)");
  		rc.voidEval("data<-readLines(\"/home/sist/newsdata.txt\")");
  		  //rc.voidEval("data<-\""+data+"\"");
  		  rc.voidEval("data2<-sapply(data, extractNoun,USE.NAMES = F)");
  		  rc.voidEval("data3<-unlist(data2)");
  		  rc.voidEval("data4<-Filter(function(x){nchar(x)>=2},data3)");
  		  rc.voidEval("data5<-table(data4)");
  		  rc.voidEval("data6<-head(sort(data5,decreasing = T),100)");
  		  rc.voidEval("my_graph=wordcloud2(data6, size=1.5,shape = \"star\")");
  		  rc.voidEval("saveWidget(my_graph,\"/home/sist/springDev3/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/ThirdProject/star.html\",selfcontained = F)");
  		  rc.voidEval("dev.off()");
  		  rc.close();
  		  
  	  }catch(Exception ex)
  	  {
  		  System.out.println(ex.getMessage());
  	  }
    }
	
}
