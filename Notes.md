
# Magazine App



Showcasing beauty products to users allowing them to view the product and to read the description in more detail. 



## Tech / Framework used

**Built with:**




- Kotlin 
- Retrofit  
- Room Database  
- Dagger Hilt





## Features

- Splash Screen
- SearchView



## Implemented 

I created seperate data layers for the UI and Network Response, I also created a Mapper interface so i can map the response model to the UI mpdel

I also implemented a networkboundresource which allows the app to work offline and makes the app feel much quicker and smooth. it has 4 key methods: 

- load the cache data 
- fetch data from network, networkboundresource triggers the request. mapping the response to the model takes place here  
- save response in to the database
- decide here whether we should fetch new data or not 


## Tests 


Basic Unit testing for room database




## Screenshots

![image1](https://user-images.githubusercontent.com/88812838/140895407-de457882-eafb-4a9f-9285-1210bed15f9f.jpg)
![image2](https://user-images.githubusercontent.com/88812838/140895671-43775034-5096-4c5d-8b98-20ad028186b9.jpg)
![image3](https://user-images.githubusercontent.com/88812838/140895680-fbc5b576-0784-4c17-b334-24b5358da8cf.jpg)
![image4](https://user-images.githubusercontent.com/88812838/140895691-acb7b07c-0b96-4beb-8c51-049743991492.jpg)


