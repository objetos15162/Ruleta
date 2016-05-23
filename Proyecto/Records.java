import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.UserInfo; 

public class Records  
{
    private UserInfo Player;
    
    public Records()
    {
         if ((UserInfo.isStorageAvailable()) && (UserInfo.getMyInfo() != null)) {
          this.Player = UserInfo.getMyInfo();
        }
    }

   public void saveRecords(int points)
    {
         if((UserInfo.isStorageAvailable()) && (this.Player != null) && ((points > this.Player.getScore())) || (this.Player.getInt(0))==0){ 
            this.Player.setScore(points);
            this.Player.setInt(0,1); 
            this.Player.store();
        }
        
        if((UserInfo.isStorageAvailable()) && (this.Player != null) && (points > this.Player.getScore())){
            this.Player.setScore(points);
            this.Player.setInt(0,1); 
            this.Player.store();
        }
    }
    
}
