import { createContext, useContext, useState } from "react";

export const AuthContext = createContext(null);

export const useAuth = () => useContext(AuthContext);

function AuthProvider({ children }) {
  const [user, setUser] = useState(null);

  return <AuthContext.Provider>{children}</AuthContext.Provider>;
}
