package org.gonevertical.pm.directory.client.application.admin;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class AdminView extends ViewImpl implements AdminPresenter.MyView {
    public interface Binder extends UiBinder<Widget, AdminView> {
    }
    
    @Inject
    public AdminView(final Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
