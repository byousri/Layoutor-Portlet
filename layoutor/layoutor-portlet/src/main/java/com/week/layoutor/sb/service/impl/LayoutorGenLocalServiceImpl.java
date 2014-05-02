package com.week.layoutor.sb.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletPreferences;
import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutPrototype;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.PortletPreferencesIds;
import com.liferay.portal.service.LayoutPrototypeServiceUtil;
import com.liferay.portal.service.LayoutServiceUtil;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.util.portlet.PortletProps;
import com.week.layoutor.sb.service.base.LayoutorGenLocalServiceBaseImpl;
import com.week.vo.Rubrique;

/**
 * The implementation of the layoutor gen local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.week.layoutor.sb.service.LayoutorGenLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Yousri
 * @see com.week.layoutor.sb.service.base.LayoutorGenLocalServiceBaseImpl
 * @see com.week.layoutor.sb.service.LayoutorGenLocalServiceUtil
 */
public class LayoutorGenLocalServiceImpl extends LayoutorGenLocalServiceBaseImpl {
    
	public void migrationPages(Long idCompany, Rubrique arbre, HttpServletRequest themeDisplay) throws Exception {
        for (Rubrique rubrique : arbre.getFilles()) {
            rubrique.setIdParent(0L);
            addPage(idCompany, rubrique, themeDisplay);
        }
        
    }
    
    private void addPage(Long idCompany, Rubrique arbre, HttpServletRequest request) throws Exception {
        if (arbre != null) {
            String typeLayout = "".equals(arbre.getType()) ? PortletProps.get("migration.pages.portlet.type").toLowerCase() : arbre.getType();
            Map<Locale, String> localeNamesMap = new HashMap<Locale, String>();
            localeNamesMap.put(LocaleUtil.getDefault(), arbre.getName());
            Layout layout = 
            		LayoutServiceUtil.addLayout(arbre.getIdGroup().longValue(), 
            		Boolean.valueOf(PortletProps.get("migration.pages.visiblity")).booleanValue(), 
            		arbre.getIdParent().longValue(), arbre.getName(), "", "", typeLayout, false, "", new ServiceContext());
            
            if (!"0".equals(arbre.getIdTemplate())) {
                updatePage(layout, idCompany, arbre.getIdTemplate(), request);
            }
            for (Rubrique arb : arbre.getFilles()) {
                arb.setIdParent(layout.getLayoutId());
                arb.setIdGroup(layout.getGroupId());
                addPage(idCompany, arb, request);
            }
        }
        
    }
    
    private void updatePage(Layout layout, Long idCompany, String idTemplate, HttpServletRequest request) throws Exception {
        
        LayoutPrototype layoutPrototype = LayoutPrototypeServiceUtil.getLayoutPrototype(Long.valueOf(idTemplate));
        Layout layoutPrototypeLayout = layoutPrototype.getLayout();
        
        LayoutServiceUtil.updateLayout(layout.getGroupId(), layout.isPrivateLayout(), layout.getLayoutId(), layoutPrototypeLayout
                .getTypeSettings());
        
        copyPreferences(request, layout, layoutPrototypeLayout);
        
        LayoutServiceUtil.updateLookAndFeel(
                layoutPrototypeLayout.getGroupId(), layoutPrototypeLayout.isPrivateLayout(),
                layoutPrototypeLayout.getLayoutId(), layout.getThemeId(),
                layout.getColorSchemeId(), layout.getCss(), false);

    }
    

    
    private void copyPreferences(HttpServletRequest request, Layout targetLayout, Layout sourceLayout) throws Exception {
        
        long companyId = targetLayout.getCompanyId();
        
        LayoutTypePortlet sourceLayoutTypePortlet = (LayoutTypePortlet) sourceLayout.getLayoutType();
        
        List<String> sourcePortletIds = sourceLayoutTypePortlet.getPortletIds();
        
        for (String sourcePortletId : sourcePortletIds) {
            
            // Copy preference
            
            PortletPreferencesIds portletPreferencesIds = PortletPreferencesFactoryUtil.getPortletPreferencesIds(request,
                    targetLayout, sourcePortletId);
            
            PortletPreferencesLocalServiceUtil.getPreferences(portletPreferencesIds);
            
            PortletPreferencesIds sourcePortletPreferencesIds = PortletPreferencesFactoryUtil.getPortletPreferencesIds(request,
                    sourceLayout, sourcePortletId);
            
            PortletPreferences sourcePreferences = PortletPreferencesLocalServiceUtil.getPreferences(sourcePortletPreferencesIds);
            
            PortletPreferencesLocalServiceUtil.updatePreferences(portletPreferencesIds.getOwnerId(), portletPreferencesIds
                    .getOwnerType(), portletPreferencesIds.getPlid(), portletPreferencesIds.getPortletId(), sourcePreferences);
            
            // Copy portlet setup
            
            PortletPreferencesLocalServiceUtil.getPreferences(companyId, PortletKeys.PREFS_OWNER_ID_DEFAULT,
                    PortletKeys.PREFS_OWNER_TYPE_LAYOUT, targetLayout.getPlid(), sourcePortletId);
            
            sourcePreferences = PortletPreferencesLocalServiceUtil.getPreferences(companyId, PortletKeys.PREFS_OWNER_ID_DEFAULT,
                    PortletKeys.PREFS_OWNER_TYPE_LAYOUT, sourceLayout.getPlid(), sourcePortletId);
            
            PortletPreferencesLocalServiceUtil.updatePreferences(PortletKeys.PREFS_OWNER_ID_DEFAULT,
                    PortletKeys.PREFS_OWNER_TYPE_LAYOUT, targetLayout.getPlid(), sourcePortletId, sourcePreferences);
        }
    }
}
