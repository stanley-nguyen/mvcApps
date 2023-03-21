package mvc;
/*
Edits:
    Sanjana 3/14/23: created file
    Bryant 3/17/23: added changed() method
    Stanley 3/17/23: updated default constructor of Model
    Stanley 3/20/23: set oldValue for firePropertyChange() in changed() to false
*/
public class Model extends Bean{
    Boolean unsavedChanges;
    String fileName;

    public Model(){
        unsavedChanges = false;
        fileName = null;
    }
    

    public void changed() {
        firePropertyChange("unsavedChanges", false, true);
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
