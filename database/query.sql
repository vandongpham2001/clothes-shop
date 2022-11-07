-- SELECT * FROM `clothes-shop`.categories 
-- where `clothes-shop`.categories.id in (select parent_id from categories);
-- SELECT * FROM `clothes-shop`.categories 
-- where `clothes-shop`.categories.id not in (select parent_id from categories);
-- SELECT * FROM `clothes-shop`.products
-- join `clothes-shop`.product_collection on `clothes-shop`.products.id=`clothes-shop`.product_collection.product_id
-- where `clothes-shop`.product_collection.collection_id=2;
-- select * from colors 
-- join product_color on colors.id = product_color.color_id
-- where product_color.product_id = 33;
-- select distinct sizes.* from sizes
-- join product_color_size on sizes.id = product_color_size.size_id
-- join product_color on product_color.id = product_color_size.product_color_id
-- join products on products.id = product_color.product_id
-- where products.id = 33;
-- select distinct sizes.* from sizes
-- join product_color_size on sizes.id = product_color_size.size_id
-- join product_color on product_color.id = product_color_size.product_color_id
-- join products on products.id = product_color.product_id
-- where products.id = 163;
-- select sizes.* from sizes
-- where sizes.id in(
-- select size_id from product_color_size
-- where product_color_size.product_color_id in 
-- (select product_color.id from product_color
-- join products on  products.id = product_color.product_id
-- where product_color.product_id=163));
-- update `clothes-shop`.products set `clothes-shop`.products.status = 1 where `clothes-shop`.products.id >0;
-- update `clothes-shop`.colors set `clothes-shop`.colors.status = 1 where `clothes-shop`.colors.id >0;
-- update `clothes-shop`.sizes set `clothes-shop`.sizes.status = 1 where `clothes-shop`.sizes.id >0;
-- update `clothes-shop`.categories set `clothes-shop`.categories.status = 1 where `clothes-shop`.categories.id >0; 


-- SELECT products.id, products.name, products.price, products.description, products.image, products.category_id,
-- product_color.thumbnail, colors.color, sizes.size, product_color_size.quantity
-- , product_promotion.promotion_id
-- , if (promotions.discount is null, 0, promotions.discount) as discount
-- FROM `clothes-shop`.products
-- left join `clothes-shop`.product_color on products.id = product_color.product_id
-- 	-- left join product_color_image on product_color.id = product_color_image.product_color_id
--     join colors on product_color.color_id = colors.id
-- 	left join `clothes-shop`.product_color_size on product_color.id = product_color_size.product_color_id
-- 		join sizes on product_color_size.size_id = sizes.id
-- left join product_promotion on products.id = product_promotion.product_id
-- 	left join promotions on product_promotion.promotion_id = promotions.id  

-- select * from product_color
-- left join product_color_size on product_color.id = product_color_size.product_color_id

-- select * from product_color_size
-- left join product_color_size on product_color.id = product_color_size.product_color_id

-- select * from product_color
-- left join product_color_image on product_color.id = product_color_image.product_color_id
-- join product_color_size on product_color.id = product_color_size.product_color_id

-- select curdate()

-- select * from product_color
-- left join product_color_size on product_color.id = product_color_size.product_color_id

-- select * from product_color_size
-- left join product_color_size on product_color.id = product_color_size.product_color_id

-- select * from product_color
-- left join product_color_image on product_color.id = product_color_image.product_color_id
-- join product_color_size on product_color.id = product_color_size.product_color_id

-- SELECT products.id, products.name, products.price, products.description, products.image, products.category_id,
-- product_color.thumbnail, colors.color, sizes.size, product_color_size.quantity
-- , product_promotion.promotion_id
-- , if (promotions.discount is null, 0, promotions.discount) as discount
-- FROM `clothes-shop`.products
-- left join `clothes-shop`.product_color on products.id = product_color.product_id
-- 	-- left join product_color_image on product_color.id = product_color_image.product_color_id
--     join colors on product_color.color_id = colors.id
-- 	left join `clothes-shop`.product_color_size on product_color.id = product_color_size.product_color_id
-- 		join sizes on product_color_size.size_id = sizes.id
-- left join product_promotion on products.id = product_promotion.product_id
-- 	left join promotions on product_promotion.promotion_id = promotions.id
-- 		and curdate() between promotions.start_date and promotions.end_date
--         and promotions.status=1;
    
-- select
--         products.* 
-- --         promotions.id, promotions.status, promotions.start_date, promotions.end_date,
--         -- if (promotions.status=0 or current_date not between promotions.start_date and promotions.end_date, 0, promotions.discount) as discount
--     from
--         products
--     left outer join
--         (
--             product_promotion
--         left outer join
--             promotions
--                 on product_promotion.promotion_id=promotions.id
--             ) 
--                 on products.id=product_promotion.product_id 
--         where
--             products.status=1 
--             and products.id=159 

-- select
--         products.* 
-- --         promotions.id, promotions.status, promotions.start_date, promotions.end_date,
--         -- if (promotions.status=0 or current_date not between promotions.start_date and promotions.end_date, 0, promotions.discount) as discount
--     from
--         products
--     left outer join
--         (
--             product_collection
--         left outer join
--             collections
--                 on product_collection.collection_id=collections.id
--             ) 
--                 on products.id=product_collection.product_id 
--         where
--             products.status=1 
--             and collections.id=3

-- select
--         products.* 
-- --         promotions.id, promotions.status, promotions.start_date, promotions.end_date,
--         -- if (promotions.status=0 or current_date not between promotions.start_date and promotions.end_date, 0, promotions.discount) as discount
--     from
--         products

--         where
--             products.status=1 
--             and products.category_id=3;
-- select p.* from products p
-- left join product_color pro on p.id = pro.product_id
-- left join product_color_size pcs on pro.id = pcs.product_color_id;
select p.* from products p 
left join product_color pro on p.id=pro.product_id
left join product_color_size pcs on pro.id=pcs.product_color_id
where p.status=1 and pcs.size_id=4 and pro.color_id=12
Group by p.id;