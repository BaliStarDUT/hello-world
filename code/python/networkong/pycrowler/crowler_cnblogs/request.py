#!/usr/bin/env/python3
# -*- coding: utf-8 -*-
__author__='drawnkid@gmail.com'
import urllib.parse
import urllib.request
def getHtml(url,values):
    user_agent='Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36';
    headers={'User-Agent':user_agent}
    data=urllib.parse.urlencode(values)
    response_result=urllib.request.urlopen(url+'?'+data).read()
    html=response_result.decode('utf-8')
    return html

def requestCnblogs(index):
    cn_blog_url='http://www.cnblogs.com/mvc/AggSite/PostList.aspx';
    print('Request data from:'+cn_blog_url)
    value={
        'CategoryId':808,
	'CategoryType' : 'SiteHome',
	'ItemListActionName' :'PostList',
	'PageIndex' : index,
	'ParentCategoryId' : 0,
	'TotalPostCount' : 4000
    }
    result=getHtml(cn_blog_url,value)
    return result
