# demo-jsp-view
In this Spring boot Restful project, in order to return a "jsp" as a view add following denedencies in pom.xml:
```shell script       
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
        </dependency>

        <dependency>
            <groupId>org.apache.tomcat.embed</groupId>
            <artifactId>tomcat-embed-jasper</artifactId>
            <scope>provided</scope>
        </dependency>
```
additionally add jsp identifiers in properties.yml:
```shell script 
spring.mvc.view.prefix=  /WEB-INF/jsp/
spring.mvc.view.suffix= .jsp   
```
basically, you need to have a directory structure as below:
```shell script 
src/webapp/WEB-INF/jsp/
```
In order to return jsp as a view in @RestController use ModelAndView as below:
```shell script 
    @GetMapping("/view")
    public ModelAndView viewRequest(Model model) {
        model.addAttribute("action","http://localhost:8811/post");
        model.addAttribute("token","token1234");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jspViewFile");
        return modelAndView;
    }
```
Enjoy!
