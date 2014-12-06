package com.bensites.java.EasyCalc.Util

import com.bensites.java.EasyCalc.Main

/**
 * Created by Ben on 12/6/14.
 */
class DataAccess {
    private static HashMap<Object, LinkedHashMap<String,Object>> storage = [:]
    def Object owner

    DataAccess(Object owner){
        if (storage.containsKey(owner)){
            storage.get(".owner")
        } else {
            storage.put(owner, [".owner":this])
        }
        this.owner = owner
    }

    def store(String key, Object value){
        if(!key.startsWith(".")) {
            storage.get(owner).put(key,value)
            return true
        } else {
            return false
        }
    }

    def get(String key){
        return storage.get(owner).get(key)
    }

    def save(){
        storage.get(owner).each{Main.console.println(it.toString())}
    }
}
