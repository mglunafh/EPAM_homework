package dbmanager;

/**
 *
 * @author dev64
 */
public class Student extends DBEntity {
    
    private String id;
    private String name;
    private String stip;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the stip
     */
    public String getStip() {
        return stip;
    }

    /**
     * @param stip the stip to set
     */
    public void setStip(String stip) {
        this.stip = stip;
    }

    @Override
    public String getTableName() {
        return "student";
    }
    
    
    
}
