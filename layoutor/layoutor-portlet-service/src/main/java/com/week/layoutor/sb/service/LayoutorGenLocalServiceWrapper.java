package com.week.layoutor.sb.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LayoutorGenLocalService}.
 *
 * @author Yousri
 * @see LayoutorGenLocalService
 * @generated
 */
public class LayoutorGenLocalServiceWrapper implements LayoutorGenLocalService,
    ServiceWrapper<LayoutorGenLocalService> {
    private LayoutorGenLocalService _layoutorGenLocalService;

    public LayoutorGenLocalServiceWrapper(
        LayoutorGenLocalService layoutorGenLocalService) {
        _layoutorGenLocalService = layoutorGenLocalService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _layoutorGenLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _layoutorGenLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _layoutorGenLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public LayoutorGenLocalService getWrappedLayoutorGenLocalService() {
        return _layoutorGenLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedLayoutorGenLocalService(
        LayoutorGenLocalService layoutorGenLocalService) {
        _layoutorGenLocalService = layoutorGenLocalService;
    }

    @Override
    public LayoutorGenLocalService getWrappedService() {
        return _layoutorGenLocalService;
    }

    @Override
    public void setWrappedService(
        LayoutorGenLocalService layoutorGenLocalService) {
        _layoutorGenLocalService = layoutorGenLocalService;
    }
}
