package net.iforums.utils;


import javax.servlet.http.HttpServletRequest;


/**
 * @author test To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ParamUtil
{
	private HttpServletRequest req = null;
//	private String mode = "";
//	private String querystring = "";

	/**
     * 构造函数
     * 
     * @param request
     * @param requestmode
     *            区分get和post
     */
	public ParamUtil(HttpServletRequest request, String requestmode)
	{
		req = request;
        /*try {
            request.setCharacterEncoding("utf8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
//		mode = requestmode;
	}

	/**
     * 默认得到不区分
     * 
     * @param request
     */
	public ParamUtil(HttpServletRequest request)
	{
		req = request;
		try {
            request.setCharacterEncoding("utf8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}

	/**
     * 得到传递参数的数字形式,如果没有这个参数,则返回normal
     * 
     * @param paramname
     * @param normal
     * @return
     */
	public int getInt(String paramname, int normal)
	{
		try
		{
			return Integer.parseInt(get_post(paramname));
		}
		catch (Exception e)
		{
			return normal;
		}
	}

	public int getInt(String paramname)
	{
		try
		{
			return Integer.parseInt(get_post(paramname));
		}
		catch (Exception e)
		{
			return 0;
		}
	}

    /**
     * 得到传递参数的字符串形式,如果没有这个参数,返回normal
     * 
     * @param paramname
     * @param normal
     * @return
     */
	public String getString(String paramname, String normal)
	{
		try
		{
			return get_post(paramname);
		}
		catch (Exception e)
		{
			return normal;
		}
	}


	public String getString(String paramname)
	{
		try
		{
			return get_post(paramname);
		}
		catch (Exception e)
		{
			return "";
		}
	}

    /**
     * 得到传递参数的长整型形式，如果没有这个参数，返回normal
     * 
     * @param paramname
     * @return
     */
	public long getLong(String paramname, long normal)
	{
		try
		{
			return Long.parseLong(get_post(paramname));
		}
		catch (Exception e)
		{
			return normal;
		}
	}


	public long getLong(String paramname)
	{
		try
		{
			return Long.parseLong(get_post(paramname));
		}
		catch (Exception e)
		{
			return (long) 0;
		}
	}

    /**
     * 得到传递参数的双精度数形式，如果没有这个参数，返回normal
     * 
     * @param paramname
     * @param normal
     * @return
     */
	public double getDouble(String paramname, double normal)
	{
		try
		{
			return Double.parseDouble(get_post(paramname));
		}
		catch (Exception e)
		{
			return normal;
		}
	}


	public double getDouble(String paramname)
	{
		try
		{
			return Double.parseDouble(get_post(paramname));
		}
		catch (Exception e)
		{
			return (double) 0;
		}
	}

    /**
     * 得到传递参数的浮点数形式，如果没有这个参数，返回normal
     * 
     * @param paramname
     * @param normal
     * @return
     */
	public float getFloat(String paramname, float normal)
	{
		try
		{
			return Float.parseFloat(get_post(paramname));
		}
		catch (Exception e)
		{
			return normal;
		}
	}


	public float getFloat(String paramname)
	{
		try
		{
			return Float.parseFloat(get_post(paramname));
		}
		catch (Exception e)
		{
			return (float) 0;
		}
	}


	public boolean getBoolean(String paramname)
	{
		try
		{
			String param = get_post(paramname).toLowerCase();
            return param.indexOf("y") > -1 || param.indexOf("on") > -1 || param.indexOf("true") > -1 || param.indexOf("1") > -1 || param.indexOf("yes") > -1;
        }
		catch (Exception e)
		{
			return false;
		}
	}

	public boolean isPost() {
        return req.getMethod().indexOf("P") > -1;
    }

    /**
     * 区分get和post
     * 
     * @param paramname
     * @return @throws
     *         Exception
     */
	private String get_post(String paramname) throws Exception
	{		
		String s = req.getParameter(paramname);
		if (s == null) {
			throw new Exception();
		}
		return s.trim();
		//return "";
	}
}
