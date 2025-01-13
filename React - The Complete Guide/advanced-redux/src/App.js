import { useSelector } from "react-redux";
import Cart from "./components/Cart/Cart";
import Layout from "./components/Layout/Layout";
import Products from "./components/Shop/Products";

function App() {
  const isCartVisibile = useSelector((state) => state.cartUi.show);

  return (
    <Layout>
      {isCartVisibile && <Cart />}
      <Products />
    </Layout>
  );
}

export default App;
