from urllib.request import urlopen
from bs4 import BeautifulSoup
from urllib.parse import urljoin

"""
url = 'https://en.wikipedia.org/wiki/Star_Wars:_The_Rise_of_Skywalker'
html=urlopen(url)
bs = BeautifulSoup(html.read(), "html.parser")


#task 1.1
#finds and prints all a tags and the href
for link in bs.find_all('a'):  
    href = link.get('href')    
    text = link.get_text()     
    full_url = urljoin(url, href)   
    print(f'LINK: {full_url} og TEXT: {text}') 
"""

"""
#task 1.2
#finds and prints all img scr tags with absolute link 
images = bs.find_all('img', src=True)
for img in images:
    srcImage = img['src']
    full_url = urljoin(url, srcImage)
    print(full_url)
"""

#task 2a
"""
The reason it does not print:
Only the top table row contains the date attribute, but encompasses more than one rows in the table.
This makes it so a lot of the rows does not contain the date attribute.
"""

#task 3
url = "https://en.wikipedia.org/wiki/Web_scraping"
html=urlopen(url)
bs = BeautifulSoup(html, "html.parser")

for link in bs.find("div",{"class":"div-col"}).find_all("a"): #seaches the div and finds all a tags
    url2 = link.get("href")
    full_url = urljoin(url, url2) #joins the old an new links together
    html2=urlopen(full_url)
    bs2 = BeautifulSoup(html2,"html.parser")
    
    for link2 in bs2.find("p"): # loops through the new link and finds p tags
        text = link2.get_text()
        print(text)