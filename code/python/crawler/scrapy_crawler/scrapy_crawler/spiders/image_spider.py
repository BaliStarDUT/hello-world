import scrapy
import urllib
from scrapy_crawler.items import ScrapyCrawlerItem
import json
import logging


class Image_Spider(scrapy.Spider):
    name = "image_spider"

    def start_requests(self):
            urls = [
                "https://www.cyjdm.com/79294.html",
            ]
            for url in urls:
                yield scrapy.Request(url=url, callback=self.parse)


    def parse(self, response):
        for result in response.css('img.aligncenter'):
            logging.info(result.attrib)
            item = ScrapyCrawlerItem()
            # item['host']=self.name
            # item['alt']=result.attrib['alt']
            item['image_urls']= [result.attrib['src']]
            logging.info(item)
            yield item

    @classmethod
    def update_settings(cls, settings):
        super().update_settings(settings)
        settings.set("SOME_SETTING", "some value", priority="spider")