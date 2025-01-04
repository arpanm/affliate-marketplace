import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import AiToolPayment from './ai-tool-payment';
import AiToolPaymentDetail from './ai-tool-payment-detail';
import AiToolPaymentUpdate from './ai-tool-payment-update';
import AiToolPaymentDeleteDialog from './ai-tool-payment-delete-dialog';

const AiToolPaymentRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<AiToolPayment />} />
    <Route path="new" element={<AiToolPaymentUpdate />} />
    <Route path=":id">
      <Route index element={<AiToolPaymentDetail />} />
      <Route path="edit" element={<AiToolPaymentUpdate />} />
      <Route path="delete" element={<AiToolPaymentDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default AiToolPaymentRoutes;
