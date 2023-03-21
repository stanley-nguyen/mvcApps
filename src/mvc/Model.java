package mvc;
/*
Edits:
    Sanjana 3/14/23: created file
    Bryant 3/17/23: added changed() method
*/
public class Model extends Bean{
    Boolean unsavedChanges;
    String fileName;

    public Model(Boolean unsavedChanges, String fileName){
        this.unsavedChanges = unsavedChanges;
        this.fileName = fileName;
    }
    public Model(){ //Default constructor for generalization
    }

    public void changed() {
        firePropertyChange("unsavedChanges", this.unsavedChanges, true);
        this.unsavedChanges = true;
    }
    
    public Boolean getUnsavedChanges() {
        return unsavedChanges;
    }
    
    public void setUnsavedChanges(Boolean unsavedChanges) {
        Boolean oldValue = this.unsavedChanges;
        this.unsavedChanges = unsavedChanges;
        firePropertyChange("unsavedChanges", oldValue, unsavedChanges);
    }
    
    // getter and setter
    public String getFileName() {
        return fileName;
    }
    
    public void setFileName(String fileName) {
        String oldValue = this.fileName;
        this.fileName = fileName;
        firePropertyChange("filename", oldValue, fileName);
    }
}
