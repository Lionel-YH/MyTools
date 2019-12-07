import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.*;

public class NewHashSet extends HashSet{
    public static void main(String args[]){
        NewHashSet newSet = new NewHashSet();
        BdHBasePerm perm1 = newSet.new BdHBasePerm(1,"Leo Bright",null,null,null,null,"read");
        BdHBasePerm perm2 = newSet.new BdHBasePerm(1,"Leo Bright",null,null,null,null,"read");
        newSet.add(null);
        newSet.add(null);
        Iterator it = newSet.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
        boolean result = newSet.contains(null);
        System.out.println(result);
//        System.out.println(perm1.equals(perm2));

    }

    class BdHBasePerm {
        private Integer userId;
        private String userName;
        private String namespace;
        private String table;
        private String family;
        private String qualifier;
        private String action;

        public BdHBasePerm(Integer userId,String userName,String namespace,String table,String family,String qualifier,String action){
            if (null!=userId){
                this.userId = userId;
            }
            if (null!=userName){
                this.userName = userName;
            }
            if (null!=namespace){
                this.namespace = namespace;
            }
            if (null!=table){
                this.table = table;
            }
            if (null!=family){
                this.family = family;
            }
            if (null!=qualifier){
                this.qualifier = qualifier;
            }
            if (null!=action){
                this.action = action;
            }
        }

        public BdHBasePerm(){

        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getNamespace() {
            return namespace;
        }

        public void setNamespace(String namespace) {
            this.namespace = namespace;
        }

        public String getTable() {
            return table;
        }

        public void setTable(String table) {
            this.table = table;
        }

        public String getFamily() {
            return family;
        }

        public void setFamily(String family) {
            this.family = family;
        }

        public String getQualifier() {
            return qualifier;
        }

        public void setQualifier(String qualifier) {
            this.qualifier = qualifier;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        @Override
        public boolean equals(Object obj){
            if (obj instanceof BdHBasePerm) {
                BdHBasePerm o = (BdHBasePerm)obj;
                if((userId==o.userId||userId.equals(o.userId))
                        &&(userName==o.userName||userName.equals(o.userName))
                        &&(namespace==o.namespace||namespace.equals(o.namespace))
                        &&(table==o.table||table.equals(o.table))
                        &&(family==o.family||family.equals(o.family))
                        &&(qualifier==o.qualifier||qualifier.equals(o.qualifier))
                        &&(action.equals(o.action))){
                    return true;
                }

            }
            return false;
        }

        @Override
        public int hashCode(){
            int h = 0;
            if(null != userId){ h += userId; }
            if(null != userName){ h += userName.hashCode(); }
            if(null != namespace){ h += namespace.hashCode(); }
            if(null != table){ h += table.hashCode(); }
            if(null != family){ h += family.hashCode(); }
            return h + action.hashCode();
        }
    }
}
