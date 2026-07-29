package DesignPattern.Creational;


public class SingletonPattern {

    public static void main(String[] args) {

        for(int i=0;i<5;i++){
            Thread t1=new Thread(()->{
                CustomConfiguration c=CustomConfiguration.getInstance("jdbc://localhost:8080", "karthik", "password");
                System.out.println(c);
            });
            t1.start();
        }
        
    }
    public static class CustomConfiguration{
        
        private static  CustomConfiguration customConfiguration;
        private String databaseURL;
        private String username;
        private String password;
        public String getDatabaseURL() {
            return databaseURL;
        }
        public void setDatabaseURL(String databaseURL) {
            this.databaseURL = databaseURL;
        }
        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
        private  CustomConfiguration() {
        }
        private static CustomConfiguration buildInstance(String url,String name,String pwd){
            CustomConfiguration c=new CustomConfiguration();
            c.setDatabaseURL(url);
            c.setPassword(name);
            c.setUsername(pwd);
            customConfiguration=c;
            return customConfiguration;
        }
        public static synchronized CustomConfiguration getInstance(String url,String name,String pwd){
            if(customConfiguration==null){
                
                return buildInstance(url, name, pwd);
            }
            System.err.println("Instance already exists,Cannot create new instance with provided,hence supplying existing instance");
            return customConfiguration;
        }
        
    }
    
}
