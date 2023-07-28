package ${baseInfo.packageName};

import ${tableClass.fullClassName};
<#if baseService??&&baseService!="">
import ${baseService};
    <#list baseService?split(".") as simpleName>
        <#if !simpleName_has_next>
            <#assign serviceSimpleName>${simpleName}</#assign>
        </#if>
    </#list>
</#if>
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * ${tableClass.remark} Service
 *
 * @author zihuv
 * @since ${.now?string('yyyy-MM-dd')}
*/
public interface ${baseInfo.fileName} extends IService<${tableClass.shortClassName}> {

}
