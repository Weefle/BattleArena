package mc.alk.arena.controllers;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import mc.alk.arena.objects.EventParams;
import mc.alk.arena.objects.MatchParams;
import mc.alk.arena.util.CaseInsensitiveMap;


public class ParamController {
	static final CaseInsensitiveMap<MatchParams> types = new CaseInsensitiveMap<MatchParams>();

	public static void addMatchType(MatchParams pi) {
		types.put(pi.getName(), pi);
		types.put(pi.getCommand(), pi);
	}
	
	public static void removeMatchType(MatchParams pi) {
		types.remove(pi.getName());
		types.remove(pi.getCommand());
	}

	public static Collection<MatchParams> getAllParams(){
		return types.values();
	}
	
	/**
	 * Returns the found matchparams
	 * If you want to change you should make a copy
	 * @param type
	 * @return
	 */
	public static MatchParams getMatchParams(String type){
		return types.get(type);
	}

	/**
	 * Return a copy of the found matchparams
	 * @param type
	 * @return
	 */
	public static MatchParams getMatchParamCopy(String type){
		MatchParams mp = types.get(type);
		if (mp == null)
			return null;
		return new MatchParams(mp);
	}

	/**
	 * Return a copy of the found event params
	 * @param type
	 * @return
	 */
	public static EventParams getEventParamCopy(String type){
		MatchParams mp = types.get(type);
		if (mp == null || !(mp instanceof EventParams))
			return null;
		return new EventParams(mp);
	}

	public static String getAvaibleTypes(Set<String> disabled) {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		HashSet<MatchParams> params = new HashSet<MatchParams>();
		params.addAll(types.values());
		for (MatchParams mp: params){
			if (disabled != null && disabled.contains(mp.getName()))
				continue;
			if (!first) sb.append(", ");
			else first = false;
			sb.append(mp.getCommand());
		}
		return sb.toString();
	}
}
