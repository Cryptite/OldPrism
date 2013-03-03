package me.botsko.prism.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import me.botsko.prism.Prism;
import me.botsko.prism.actionlibs.ActionType;
import me.botsko.prism.commandlibs.CallInfo;
import me.botsko.prism.commandlibs.SubHandler;

public class ParamsCommand implements SubHandler {
	
	
	/**
	 * Handle the command
	 */
	public void handle(CallInfo call) {
		help(call.getSender());
	}
	
	
	/**
	 * Display param help
	 * @param player
	 */
	private void help( CommandSender sender ) {
		
		sender.sendMessage( Prism.messenger.playerHeaderMsg( ChatColor.GOLD + "--- Parameters Help ---" ) );

		sender.sendMessage( Prism.messenger.playerMsg( ChatColor.LIGHT_PURPLE + "a:[action]" + ChatColor.WHITE + " Like 'block-break' (See below for full list). No default."));
		sender.sendMessage( Prism.messenger.playerMsg( ChatColor.LIGHT_PURPLE + "r:[radius]" + ChatColor.WHITE + " i.e. 20, or 100. Defaults to default-radius defined in config. Use r:global to force an all-world search, for lookups only. To use a different player's location, use p:<playername>:<radius>, like p:viveleroi:20") );
		sender.sendMessage( Prism.messenger.playerMsg( ChatColor.LIGHT_PURPLE + "p:[player]" + ChatColor.WHITE + " Like 'viveleroi'. No default.") );
		sender.sendMessage( Prism.messenger.playerMsg( ChatColor.LIGHT_PURPLE + "b:[block]" + ChatColor.WHITE + " Like 'grass' or '2' or '2:0'. No default.") );
		sender.sendMessage( Prism.messenger.playerMsg( ChatColor.LIGHT_PURPLE + "e:[entity]" + ChatColor.WHITE + " Like 'pig'. No default.") );
		sender.sendMessage( Prism.messenger.playerMsg( ChatColor.LIGHT_PURPLE + "t:[time]" + ChatColor.WHITE + " Events since x long ago. Like 1s(seconds), 20m(minutes), 1h(hour), 7d(days), 2w(weeks). Default based on config.") );
		sender.sendMessage( Prism.messenger.playerMsg( ChatColor.LIGHT_PURPLE + "since:[time]" + ChatColor.WHITE + " Events since to x long ago (same as t:).") );
		sender.sendMessage( Prism.messenger.playerMsg( ChatColor.LIGHT_PURPLE + "before:[time]" + ChatColor.WHITE + " Events prior to x long ago.") );
		sender.sendMessage( Prism.messenger.playerMsg( ChatColor.LIGHT_PURPLE + "w:[world]" + ChatColor.WHITE + " Defaults to your current world.") );
		sender.sendMessage( Prism.messenger.playerMsg( ChatColor.LIGHT_PURPLE + "k:[text]" + ChatColor.WHITE + " Keyword search. Mainly for command/chat logging.") );
		sender.sendMessage( Prism.messenger.playerMsg( ChatColor.LIGHT_PURPLE + "id:[#]" + ChatColor.WHITE + " Record id. Useful for single item rollbacks/restores without a wand.") );
		sender.sendMessage( Prism.messenger.playerMsg( "Prefix action, player, entity names with ! to exclude. Like p:!viveleroi") );
		
		// Build short list
		ArrayList<String> shortNames = new ArrayList<String>();
		HashMap<String,ActionType> actions = Prism.getActionRegistry().getRegisteredAction();
		for (Entry<String,ActionType> entry : actions.entrySet()){
			if(entry.getKey().contains("prism")) continue;
			if(shortNames.contains( entry.getValue().getShortName() )) continue;
			shortNames.add( entry.getValue().getShortName() );
		}
		// Sort alphabetically
		Collections.sort(shortNames);
		
		// Build display of shortname list
		String actionList = "";
		int i = 1;
		for(String shortName : shortNames){
			actionList += shortName + (i < shortNames.size() ? ", " : "");
			i++;
		}
		sender.sendMessage( Prism.messenger.playerMsg( ChatColor.LIGHT_PURPLE + "Action Aliases:" + ChatColor.WHITE + " " + actionList) );
		
		// Build display of full actions
		actionList = "";
		i = 1;
		for (Entry<String,ActionType> entry : actions.entrySet()){
			if(entry.getKey().contains("prism")) continue;
			actionList += entry.getKey() + (i < actions.size() ? ", " : "");
			i++;
		}
		sender.sendMessage( Prism.messenger.playerMsg( ChatColor.LIGHT_PURPLE + "Full Actions:" + ChatColor.GRAY + " " + actionList) );
		
	}
}