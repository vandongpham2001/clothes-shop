SELECT * FROM `clothes-shop`.categories 
where `clothes-shop`.categories.id in (select parent_id from categories);
SELECT * FROM `clothes-shop`.categories 
where `clothes-shop`.categories.id not in (select parent_id from categories);
-- update `clothes-shop`.products set `clothes-shop`.products.status = 1 where `clothes-shop`.products.id >0;
-- update `clothes-shop`.colors set `clothes-shop`.colors.status = 1 where `clothes-shop`.colors.id >0;
-- update `clothes-shop`.sizes set `clothes-shop`.sizes.status = 1 where `clothes-shop`.sizes.id >0;
-- update `clothes-shop`.categories set `clothes-shop`.categories.status = 1 where `clothes-shop`.categories.id >0;