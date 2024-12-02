<h1>Invalidate/Blacklist the JWT using Redis: Logout Mechanism in Spring Security</h1>

In my blog post titled “Invalidate/Revoked the JWT : Force logout the user from spring security” on Medium I’ve detailed how to invalidate JWT tokens to log out users. I’ve explored various options for invalidating JWT tokens and implemented the blacklist approach as follows:

Establishing a JWT blacklist can be a useful strategy. Depending on the token’s expiration time, there might be a delay between when the client deletes its token and when it becomes invalid. If you desire immediate invalidation, you can implement a token blacklist. When the server receives a logout request, it extracts the JWT from the request and stores it in an in-memory database. For every authenticated request, the system checks the in-memory database to determine if the token has been invalidated. To manage the search space efficiently, you can remove tokens from the blacklist once they have expired.

I also recommended implementing the JWT blacklist as a means to invalidate JWTs, storing the blacklist in a caching database rather than relying on in-memory storage. Due to numerous requests for an article on how to set up JWT blacklisting in a caching database rather than in-memory, I’ve decided to create a detailed guide on this topic.

![Logout (1)](https://github.com/user-attachments/assets/3d4cb47f-2ac4-4d42-aadf-31afec94b891)


Follow my below blog to to understand the step by step implementation:

https://levelup.gitconnected.com/invalidate-blacklist-the-jwt-using-redis-logout-mechanism-in-spring-security-86b23149699a

<h1>❤️ Support & Engagement ❤️</h1>

⭐ If you find this project helpful, please give it a star on [GitHub](https://github.com/zees007/invalidate-blacklist-jwt-using-redis-spring-boot))! 

⭐ If you find this article informative and beneficial, please consider showing your appreciation by giving it a clap 👏👏👏, highlight it and replying on my story story. Feel free to share this article with your peers. Your support and knowledge sharing within the developer community are highly valued.

⭐ Please share on social media

⭐ Follow me on : [Medium](https://medium.com/@mhmdzeeshan) || [LinkedIn](https://www.linkedin.com/in/zeeshan-adil-a94b3867/) || [X (Formerly Twitter)](https://x.com/DevZeesCraft)

⭐ Check out my work, projects, and more on my [Linktree](https://linktr.ee/zees007)

⭐ [Check out my other articles on Medium](https://medium.com/@mhmdzeeshan)

⭐ [Subscribe to my newsletter 📧](https://medium.com/@mhmdzeeshan/subscribe), so that you don’t miss out on my latest articles.

⭐ If you enjoyed my article, please consider [buying me a coffee ❤️](https://buymeacoffee.com/mhmdzeeshan) and stay tuned to more articles about java, technologies and AI. 🧑‍💻


## Contact me

<div align="center">
  <a href="https://www.linkedin.com/in/zeeshan-adil-a94b3867/" target="_blank">
    <img src="https://img.shields.io/static/v1?message=LinkedIn&logo=linkedin&label=&color=0077B5&logoColor=white&labelColor=&style=flat" height="35" alt="linkedin logo"  />
  </a>
  <img width="20" />
  <a href="https://medium.com/@mhmdzeeshan" target="_blank">
    <img src="https://img.shields.io/static/v1?message=medium&logo=medium&label=&color=black&logoColor=white&labelColor=&style=flat" height="35" alt="medium logo"  />
  </a>
  <img width="20" />
  <a href="https://facebook.com/groups/321306497491174/?ref=share_group_link" target="_blank">
    <img src="https://img.shields.io/static/v1?message=facebook&logo=facebook&label=&color=1877F2&logoColor=white&labelColor=&style=flat" height="35" alt="stackoverflow logo"  />
  </a>
</div>
