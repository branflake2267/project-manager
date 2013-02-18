package eclipseplugin.wizards;

import org.eclipse.jface.wizard.Wizard;

public class NewProject extends Wizard {

  public NewProject() {
    setWindowTitle("New Wizard");
  }

  @Override
  public void addPages() {
  }

  @Override
  public boolean performFinish() {
    return false;
  }

}
