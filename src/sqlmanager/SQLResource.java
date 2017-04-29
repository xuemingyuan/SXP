
package sqlmanager;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.digester3.Digester;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultElement;
import org.omg.CORBA.SystemException;
import org.springframework.core.io.Resource;
import org.xml.sax.SAXException;

public class SQLResource
{
    private Resource[] locations;
    
    public Resource[] getLocations()
    {
        return locations;
    }
    
    public void setLocations(Resource[] locations)
    {
        this.locations = locations;
    }
    
    private Properties properties = new Properties();
    
    public Properties getProperties()
    {
        return properties;
    }
    
    public void setProperties(Properties properties)
    {
        this.properties = properties;
    }
    
    public static void main(String[] args)
        throws Exception
    {
        SQLResource sqlresource = new SQLResource();
        sqlresource.init();
    }
    
    public void init()throws Exception
    {
        for (Resource path : locations)
        {
            SAXReader reader = new SAXReader();
            Document document = reader.read(path.getFile());// ��ȡXML�ļ�
            List list = document.selectNodes("//sql");
            for (int i = 0; i < list.size(); i++)
            {
                DefaultElement e = (DefaultElement)list.get(i);
                String key = e.attribute("id").getData().toString();
                String value = e.getData().toString();
                addSqlInfo(key, value);
            }
        }
    }
    
    private void loadSqlFile(InputStream is)
        throws IOException, SAXException, SystemException
    {
        
        Digester digester = new Digester();
        // ����Ҫ���ñ�Class�У�this���ķ���
        digester.push(this);
        
        // ��XML��sqlList/sql�ײ��е����ݣ�����addSqlInfo�����������÷�����������Ϊ2
        digester.addCallMethod("sqlList/sql", "addSqlInfo", 2);
        
        // ��sqlId�е�ֵ���������ĵ�һ������
        digester.addCallParam("sqlList/sql", 0, "id");
        // ��<![CDATA[�е�ֵ���������ĵڶ�������
        digester.addCallParam("sqlList/sql", 1);
        
        // ����XML�ļ�
        digester.parse(is);
    }
    
    public void addSqlInfo(final String sqlKey, String sql)
        throws SystemException
    {
        String sqlValue = sql;
        if (sqlKey == null)
        {
            //throw new SystemException();
        }
        
        // delete /*...*/
        Pattern pattern = Pattern.compile("(//*).*?(/*/)");
        Matcher matcher = pattern.matcher(sqlValue);
        sqlValue = matcher.replaceAll("");
        
        // delete --
        pattern = Pattern.compile("(--).*? ");
        matcher = pattern.matcher(sqlValue);
        sqlValue = matcher.replaceAll("");
        
        // replace  
        pattern = Pattern.compile("[ ]");
        matcher = pattern.matcher(sqlValue);
        sqlValue = matcher.replaceAll(" ");
        
        // replace 	
        pattern = Pattern.compile("[	]+");
        matcher = pattern.matcher(sqlValue);
        sqlValue = matcher.replaceAll(" ");
        
        sqlValue = sqlValue.trim();
        
        this.properties.put(sqlKey, sqlValue);
    }
}