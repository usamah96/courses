import ProductItem from "./ProductItem";
import classes from "./Products.module.css";

const DUMMY_PRODUCTS = [
  {
    id: "p1",
    title: "Book One",
    price: 50,
    description: "This is my first Book",
  },
  {
    id: "p2",
    title: "Book Two",
    price: 42,
    description: "This is my second Book",
  },
  {
    id: "p3",
    title: "Book Three",
    price: 86,
    description: "This is my third Book",
  },
];

const Products = (props) => {
  return (
    <section className={classes.products}>
      <h2>Buy your favorite products</h2>
      <ul>
        {DUMMY_PRODUCTS.map((product) => (
          <ProductItem
            id={product.id}
            key={product.id}
            title={product.title}
            price={product.price}
            description={product.description}
          />
        ))}
      </ul>
    </section>
  );
};

export default Products;
