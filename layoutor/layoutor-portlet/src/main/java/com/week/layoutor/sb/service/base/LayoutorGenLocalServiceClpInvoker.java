package com.week.layoutor.sb.service.base;

import com.week.layoutor.sb.service.LayoutorGenLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Yousri
 * @generated
 */
public class LayoutorGenLocalServiceClpInvoker {
    private String _methodName14;
    private String[] _methodParameterTypes14;
    private String _methodName15;
    private String[] _methodParameterTypes15;

    public LayoutorGenLocalServiceClpInvoker() {
        _methodName14 = "getBeanIdentifier";

        _methodParameterTypes14 = new String[] {  };

        _methodName15 = "setBeanIdentifier";

        _methodParameterTypes15 = new String[] { "java.lang.String" };
    }

    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
        if (_methodName14.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
            return LayoutorGenLocalServiceUtil.getBeanIdentifier();
        }

        if (_methodName15.equals(name) &&
                Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
            LayoutorGenLocalServiceUtil.setBeanIdentifier((java.lang.String) arguments[0]);

            return null;
        }

        throw new UnsupportedOperationException();
    }
}
