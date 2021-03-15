package me.chocolf.moneyfrommobs.integrations;

import org.bukkit.entity.Player;

import me.chocolf.moneyfrommobs.MoneyFromMobs;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

/**
 * This class will be registered through the register-method in the 
 * plugins onEnable-method.
 */
public class PlaceholderAPI extends PlaceholderExpansion {

    private MoneyFromMobs plugin;

    public PlaceholderAPI(MoneyFromMobs plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean persist(){
        return true;
    }

    @Override
    public boolean canRegister(){
        return true;
    }

    @Override
    public String getAuthor(){
        return plugin.getDescription().getAuthors().toString();
    }


    @Override
    public String getIdentifier(){
        return "moneyfrommobs";
    }


    @Override
    public String getVersion(){
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player p, String identifier){

        if(p == null){
            return "";
        }

        // %moneyfrommobs_latest_picked_up%
        if(identifier.equals("latest_picked_up")){
        	if (plugin.getPlaceholdersListener().latestPickedUp.containsKey(p.getUniqueId())) {
        		return plugin.getPlaceholdersListener().latestPickedUp.get(p.getUniqueId());
        	}
        	else {
        		String itemName = plugin.getManager().getItemName();
        		itemName = itemName.replace("%amount%", "0.00");
        		return itemName;
        	}
        	
        }

//        // %someplugin_placeholder2%
//        if(identifier.equals("placeholder2")){
//            return plugin.getConfig().getString("placeholder2", "value doesnt exist");
//        }
 
        // We return null if an invalid placeholder (f.e. %someplugin_placeholder3%) 
        // was provided
        return null;
    }
}