import React from 'react';
import { Route } from 'react-router';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import ReviewChangeHistory from './review-change-history';
import ReviewChangeHistoryDetail from './review-change-history-detail';
import ReviewChangeHistoryUpdate from './review-change-history-update';
import ReviewChangeHistoryDeleteDialog from './review-change-history-delete-dialog';

const ReviewChangeHistoryRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<ReviewChangeHistory />} />
    <Route path="new" element={<ReviewChangeHistoryUpdate />} />
    <Route path=":id">
      <Route index element={<ReviewChangeHistoryDetail />} />
      <Route path="edit" element={<ReviewChangeHistoryUpdate />} />
      <Route path="delete" element={<ReviewChangeHistoryDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default ReviewChangeHistoryRoutes;
