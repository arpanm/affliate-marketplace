import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import CompetitionPaymentToWinner from './competition-payment-to-winner';
import CompetitionPaymentToWinnerDetail from './competition-payment-to-winner-detail';
import CompetitionPaymentToWinnerUpdate from './competition-payment-to-winner-update';
import CompetitionPaymentToWinnerDeleteDialog from './competition-payment-to-winner-delete-dialog';

const CompetitionPaymentToWinnerRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<CompetitionPaymentToWinner />} />
    <Route path="new" element={<CompetitionPaymentToWinnerUpdate />} />
    <Route path=":id">
      <Route index element={<CompetitionPaymentToWinnerDetail />} />
      <Route path="edit" element={<CompetitionPaymentToWinnerUpdate />} />
      <Route path="delete" element={<CompetitionPaymentToWinnerDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default CompetitionPaymentToWinnerRoutes;
