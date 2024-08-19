# invalidate-blacklist-jwt-using-redis-spring-boot

<h1>Invalidate/Blacklist the JWT using Redis: Logout Mechanism in Spring Security</h1>

In my blog post titled “Invalidate/Revoked the JWT : Force logout the user from spring security” on Medium I’ve detailed how to invalidate JWT tokens to log out users. I’ve explored various options for invalidating JWT tokens and implemented the blacklist approach as follows:

Establishing a JWT blacklist can be a useful strategy. Depending on the token’s expiration time, there might be a delay between when the client deletes its token and when it becomes invalid. If you desire immediate invalidation, you can implement a token blacklist. When the server receives a logout request, it extracts the JWT from the request and stores it in an in-memory database. For every authenticated request, the system checks the in-memory database to determine if the token has been invalidated. To manage the search space efficiently, you can remove tokens from the blacklist once they have expired.

I also recommended implementing the JWT blacklist as a means to invalidate JWTs, storing the blacklist in a caching database rather than relying on in-memory storage. Due to numerous requests for an article on how to set up JWT blacklisting in a caching database rather than in-memory, I’ve decided to create a detailed guide on this topic.

Follow my below blog to to understand the step by step implementation:

https://levelup.gitconnected.com/invalidate-blacklist-the-jwt-using-redis-logout-mechanism-in-spring-security-86b23149699a

## Contact me

<div align="center">
  <span style="padding: 0 10px;">
    <a href="https://www.linkedin.com/in/zeeshan-adil-a94b3867/" target="_blank">
      <img src="https://img.shields.io/static/v1?message=LinkedIn&logo=linkedin&label=&color=0077B5&logoColor=white&labelColor=&style=flat" height="35" alt="linkedin logo" />
    </a>
  </span>
  <span style="padding: 0 10px;">
    <a href="https://medium.com/@mhmdzeeshan" target="_blank">
      <img src="https://img.shields.io/static/v1?message=medium&logo=medium&label=&color=black&logoColor=white&labelColor=&style=flat" height="35" alt="medium logo" />
    </a>
  </span>
  <span style="padding: 0 10px;">
    <a href="https://facebook.com/groups/321306497491174/?ref=share_group_link" target="_blank">
      <img src="https://img.shields.io/static/v1?message=facebook&logo=facebook&label=&color=1877F2&logoColor=white&labelColor=&style=flat" height="35" alt="facebook logo" />
    </a>
  </span>
</div>
